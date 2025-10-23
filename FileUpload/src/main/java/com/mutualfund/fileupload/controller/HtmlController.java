package com.mutualfund.fileupload.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mutualfund.fileupload.model.StockDto;
import com.mutualfund.fileupload.service.StockExtractService;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/htmlText")
public class HtmlController {

	private final StockExtractService service;

	public HtmlController(StockExtractService service) {
		this.service = service;
	}

	// Accept raw HTML in request body (application/json with { "html": "...",
	// "base": "https://..." })
	@PostMapping(path = "/extract", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> extractFromJson(@RequestBody HtmlRequest req) {
		List<StockDto> list = service.extractFromHtml(req.getHtml(), req.getBase());
		return ResponseEntity.ok(list);
	}

	// Accept form upload of a file (multipart/form-data)
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> uploadFile(@RequestPart("file") MultipartFile file,
			@RequestPart(value = "base", required = false) String base) throws Exception {
		String html = new String(file.getBytes(), StandardCharsets.UTF_8);
		List<StockDto> list = service.extractFromHtml(html, base);
		return ResponseEntity.ok(list);
	}

	// Small DTO used by extractFromJson endpoint
	public static class HtmlRequest {
		private String html;
		private String base;

		public HtmlRequest() {
		}

		public String getHtml() {
			return html;
		}

		public void setHtml(String html) {
			this.html = html;
		}

		public String getBase() {
			return base;
		}

		public void setBase(String base) {
			this.base = base;
		}
	}
}