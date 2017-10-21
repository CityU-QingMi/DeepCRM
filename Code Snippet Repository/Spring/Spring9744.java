	@Override
	public void initBinder(WebDataBinder binder, NativeWebRequest request) throws Exception {
		for (InvocableHandlerMethod binderMethod : this.binderMethods) {
			if (isBinderMethodApplicable(binderMethod, binder)) {
				Object returnValue = binderMethod.invokeForRequest(request, null, binder);
				if (returnValue != null) {
					throw new IllegalStateException(
							"@InitBinder methods should return void: " + binderMethod);
				}
			}
		}
	}
