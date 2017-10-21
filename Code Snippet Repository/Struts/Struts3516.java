	public void doExecute(String finalLocation, ActionInvocation actionInvocation) throws Exception {

        PortletPhase phase = PortletActionContext.getPhase();
        if (phase.isRender() || phase.isResource()) {
			executeMimeResult(finalLocation);
		} else if (phase.isAction() || phase.isEvent()) {
			executeActionResult(finalLocation, actionInvocation);
		} else {
			executeRegularServletResult(finalLocation, actionInvocation);
		}
	}
