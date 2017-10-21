		@RequestMapping(value = "", method = RequestMethod.POST)
		public String processMultipartFileList(@RequestParam List<MultipartFile> file,
				@RequestPart Map<String, String> json, Model model) throws IOException {

			byte[] content = file.get(0).getBytes();
			Assert.assertArrayEquals(content, file.get(1).getBytes());
			model.addAttribute("fileContent", content);
			model.addAttribute("jsonContent", json);

			return "redirect:/index";
		}
