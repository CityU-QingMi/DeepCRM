		@Override
		public boolean test(ServerRequest request) {
			PathContainer pathContainer = request.pathContainer();
			PathPattern.PathMatchInfo info = this.pattern.matchAndExtract(pathContainer);
			traceMatch("Pattern", this.pattern.getPatternString(), request.path(), info != null);
			if (info != null) {
				mergeTemplateVariables(request, info.getUriVariables());
				return true;
			}
			else {
				return false;
			}
		}
