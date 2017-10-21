	protected final void callNextHandlerInChain(
			FacesContext facesContext, @Nullable String fromAction, @Nullable String outcome, @Nullable NavigationHandler originalNavigationHandler) {

		NavigationHandler decoratedNavigationHandler = getDecoratedNavigationHandler();

		if (decoratedNavigationHandler instanceof DecoratingNavigationHandler) {
			// DecoratingNavigationHandler specified through constructor argument:
			// Call it with original NavigationHandler passed in.
			DecoratingNavigationHandler decHandler = (DecoratingNavigationHandler) decoratedNavigationHandler;
			decHandler.handleNavigation(facesContext, fromAction, outcome, originalNavigationHandler);
		}
		else if (decoratedNavigationHandler != null) {
			// Standard NavigationHandler specified through constructor argument:
			// Call it through standard API, without original NavigationHandler passed in.
			// The called handler will not be able to redirect to the original handler.
			decoratedNavigationHandler.handleNavigation(facesContext, fromAction, outcome);
		}
		else if (originalNavigationHandler != null) {
			// No NavigationHandler specified through constructor argument:
			// Call original handler, marking the end of this chain.
			originalNavigationHandler.handleNavigation(facesContext, fromAction, outcome);
		}
	}
