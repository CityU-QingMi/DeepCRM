		@RequestMapping(value = "", method = RequestMethod.POST)
		public String processOptionalFile(@RequestParam Optional<MultipartFile> file,
				@RequestPart Map<String, String> json, Model model) throws IOException {

			if (file.isPresent()) {
				model.addAttribute("fileContent", file.get().getBytes());
			}
			model.addAttribute("jsonContent", json);

			return "redirect:/index";
		}
