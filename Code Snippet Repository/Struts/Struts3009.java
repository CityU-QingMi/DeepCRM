	private void restoreNestedVariables() {
		if (nestedVars != null) {
			Iterator iter = nestedVars.iterator();
			while (iter.hasNext()) {
				String varName = (String) iter.next();
				varName = findAlias(varName);
				Object obj = originalNestedVars.get(varName);
				if (obj != null) {
					invokingJspCtxt.setAttribute(varName, obj);
				} else {
					invokingJspCtxt.removeAttribute(varName, PAGE_SCOPE);
				}
			}
		}
	}
