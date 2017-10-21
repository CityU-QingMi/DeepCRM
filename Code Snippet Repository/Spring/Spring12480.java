	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			ScriptEngine engine = getEngine();
			String url = getUrl();
			Assert.state(url != null, "'url' not set");
			String template = getTemplate(url);

			Function<String, String> templateLoader = path -> {
				try {
					return getTemplate(path);
				}
				catch (IOException ex) {
					throw new IllegalStateException(ex);
				}
			};

			Locale locale = RequestContextUtils.getLocale(request);
			RenderingContext context = new RenderingContext(obtainApplicationContext(), locale, templateLoader, url);

			Object html;
			if (this.renderFunction == null) {
				SimpleBindings bindings = new SimpleBindings();
				bindings.putAll(model);
				model.put("renderingContext", context);
				html = engine.eval(template, bindings);
			}
			else if (this.renderObject != null) {
				Object thiz = engine.eval(this.renderObject);
				html = ((Invocable) engine).invokeMethod(thiz, this.renderFunction, template, model, context);
			}
			else {
				html = ((Invocable) engine).invokeFunction(this.renderFunction, template, model, context);
			}

			response.getWriter().write(String.valueOf(html));
		}
		catch (ScriptException ex) {
			throw new ServletException("Failed to render script template", new StandardScriptEvalException(ex));
		}
	}
