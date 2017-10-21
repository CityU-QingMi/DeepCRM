	private int getAspectDeclarationOrder(Advisor anAdvisor) {
		AspectJPrecedenceInformation precedenceInfo =
			AspectJAopUtils.getAspectJPrecedenceInformationFor(anAdvisor);
		if (precedenceInfo != null) {
			return precedenceInfo.getDeclarationOrder();
		}
		else {
			return 0;
		}
	}
