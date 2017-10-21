		@RequestMapping(value = "", method = RequestMethod.POST)
		public String processMultipartFileArray(@RequestParam MultipartFile[] file,
				@RequestPart Map<String, String> json, Model model) throws IOException {

			byte[] content = file[0].getBytes();
			Assert.assertArrayEquals(content, file[1].getBytes());
			model.addAttribute("fileContent", content);
			model.addAttribute("jsonContent", json);

			return "redirect:/index";
		}
