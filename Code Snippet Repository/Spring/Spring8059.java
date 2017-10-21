	private void doMarshal(Object graph, HierarchicalStreamWriter streamWriter, @Nullable DataHolder dataHolder) {
		try {
			getXStream().marshal(graph, streamWriter, dataHolder);
		}
		catch (Exception ex) {
			throw convertXStreamException(ex, true);
		}
		finally {
			try {
				streamWriter.flush();
			}
			catch (Exception ex) {
				logger.debug("Could not flush HierarchicalStreamWriter", ex);
			}
		}
	}
