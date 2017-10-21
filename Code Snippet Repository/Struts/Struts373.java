	private Vector<String> checkEntries(String[] entries, File parent, String prefix) {
		
		if (entries == null ) {
			return null;
		}

		Vector<String> matches = new Vector<>();
		for (String listEntry : entries) {
			File tempFile ;
			if (!"".equals(prefix) ) {
				tempFile = new File(parent, prefix + "/" + listEntry);
			}
			else {
				tempFile = new File(parent, listEntry);
			}
			if (tempFile.isDirectory() && 
					!(".".equals(listEntry) || "..".equals(listEntry)) ) {
				if	(!"".equals(prefix) ) {
					matches.addAll(checkEntries(tempFile.list(), parent, prefix + "/" + listEntry));
				}
				else {
					matches.addAll(checkEntries(tempFile.list(), parent, listEntry));
				}
			}
			else {
				
				String entryToCheck ;
				if ("".equals(prefix)) {
					entryToCheck = listEntry ;
				}
				else {
					entryToCheck = prefix + "/" + listEntry ;
				}
				
				if (compared.contains(entryToCheck) ) {
					continue;
				}
				else {
					compared.add(entryToCheck) ;
				}
				
				boolean doesMatch = patternMatcher.match(new HashMap<String,String>(), entryToCheck, compiledPattern);
				if (doesMatch) {
					matches.add(entryToCheck);
				}
			}
		}
		return matches ;
	}
