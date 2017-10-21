	private void copyTagToPageScope(int scope) {
		Iterator iter = null;

		switch (scope) {
		case VariableInfo.NESTED:
			if (nestedVars != null) {
				iter = nestedVars.iterator();
			}
			break;
		case VariableInfo.AT_BEGIN:
			if (atBeginVars != null) {
				iter = atBeginVars.iterator();
			}
			break;
		case VariableInfo.AT_END:
			if (atEndVars != null) {
				iter = atEndVars.iterator();
			}
			break;
		}

		while ((iter != null) && iter.hasNext()) {
			String varName = (String) iter.next();
			Object obj = getAttribute(varName);
			varName = findAlias(varName);
			if (obj != null) {
				invokingJspCtxt.setAttribute(varName, obj);
			} else {
				invokingJspCtxt.removeAttribute(varName, PAGE_SCOPE);
			}
		}
	}
