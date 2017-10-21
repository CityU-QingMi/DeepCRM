	private void saveNestedVariables() {
		if (nestedVars != null) {
			Iterator iter = nestedVars.iterator();
			while (iter.hasNext()) {
				String varName = (String) iter.next();
				varName = findAlias(varName);
				Object obj = invokingJspCtxt.getAttribute(varName);
				if (obj != null) {
					originalNestedVars.put(varName, obj);
				}
			}
		}
	}
