	@Override
	public boolean hasNext() {
		try {
			return peekedEvent != null || (super.hasNext() && this.peek() != null);
		}
		catch (XMLStreamException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
