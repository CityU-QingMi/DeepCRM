	private void printReportEntry(String indent, ReportEntry reportEntry) {
		out.println();
		out.print(indent);
		out.print(reportEntry.getTimestamp());
		Set<Map.Entry<String, String>> entries = reportEntry.getKeyValuePairs().entrySet();
		if (entries.size() == 1) {
			printReportEntry(" ", getOnlyElement(entries));
			return;
		}
		for (Map.Entry<String, String> entry : entries) {
			out.println();
			printReportEntry(indent + theme.blank(), entry);
		}
	}
