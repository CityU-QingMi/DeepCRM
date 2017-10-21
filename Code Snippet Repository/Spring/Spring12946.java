		public String handle(
				@CookieValue("cookie") int cookie,
				@PathVariable("pathvar") String pathvar,
				@RequestHeader("header") String header,
				@RequestHeader(defaultValue = "#{systemProperties.systemHeader}") String systemHeader,
				@RequestHeader Map<String, Object> headerMap,
				@RequestParam("dateParam") Date dateParam,
				@RequestParam Map<String, Object> paramMap,
				String paramByConvention,
				@Value("#{request.contextPath}") String value,
				@ModelAttribute("modelAttr") @Valid TestBean modelAttr,
				Errors errors,
				TestBean modelAttrByConvention,
				Color customArg,
				HttpServletRequest request,
				HttpServletResponse response,
				@SessionAttribute TestBean sessionAttribute,
				@RequestAttribute TestBean requestAttribute,
				User user,
				@ModelAttribute OtherUser otherUser,
				Model model,
				UriComponentsBuilder builder) throws Exception {

			model.addAttribute("cookie", cookie).addAttribute("pathvar", pathvar).addAttribute("header", header)
					.addAttribute("systemHeader", systemHeader).addAttribute("headerMap", headerMap)
					.addAttribute("dateParam", dateParam).addAttribute("paramMap", paramMap)
					.addAttribute("paramByConvention", paramByConvention).addAttribute("value", value)
					.addAttribute("customArg", customArg).addAttribute(user)
					.addAttribute("sessionAttribute", sessionAttribute)
					.addAttribute("requestAttribute", requestAttribute)
					.addAttribute("url", builder.path("/path").build().toUri());

			assertNotNull(request);
			assertNotNull(response);

			return "viewName";
		}
