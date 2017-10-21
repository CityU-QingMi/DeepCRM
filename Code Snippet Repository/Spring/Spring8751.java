		@RequestMapping(value = "", method = RequestMethod.POST)
		public String processOptionalFileList(@RequestParam Optional<List<MultipartFile>> file,
				@RequestPart Map<String, String> json, Model model) throws IOException {

			if (file.isPresent()) {
				byte[] content = file.get().get(0).getBytes();
				Assert.assertArrayEquals(content, file.get().get(1).getBytes());
				model.addAttribute("fileContent", content);
			}
			model.addAttribute("jsonContent", json);

			return "redirect:/index";
		}
