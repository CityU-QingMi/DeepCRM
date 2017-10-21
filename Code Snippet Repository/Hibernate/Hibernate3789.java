	private void dumpJoins(Iterable<Join> joins, int depth, PrintWriter printWriter) {
		for ( Join join : joins ) {
			printWriter.println(
					TreePrinterHelper.INSTANCE.generateNodePrefix( depth )
							+ "JOIN (" + join.getLeftHandSide().getUid() + " -> " + join.getRightHandSide()
							.getUid() + ")"
			);
			dumpQuerySpace( join.getRightHandSide(), depth+1, printWriter );
		}
	}
