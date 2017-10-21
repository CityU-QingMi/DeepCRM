	@Override
	public void handleNavigation(FacesContext facesContext, String fromAction, String outcome) {
		NavigationHandler handler = getDelegate(facesContext);
		if (handler instanceof DecoratingNavigationHandler) {
			((DecoratingNavigationHandler) handler).handleNavigation(
					facesContext, fromAction, outcome, this.originalNavigationHandler);
		}
		else {
			handler.handleNavigation(facesContext, fromAction, outcome);
		}
	}
