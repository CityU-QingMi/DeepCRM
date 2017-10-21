		@Override
		protected Map<Object, Object> constructMapping(MappingNode node) {
			try {
				return super.constructMapping(node);
			}
			catch (IllegalStateException ex) {
				throw new ParserException("while parsing MappingNode",
						node.getStartMark(), ex.getMessage(), node.getEndMark());
			}
		}
