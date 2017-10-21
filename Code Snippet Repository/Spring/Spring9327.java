		@Override
		public Publisher<? extends List<XMLEvent>> apply(XMLEvent event) {
			if (event.isStartElement()) {
				if (this.barrier == Integer.MAX_VALUE) {
					QName startElementName = event.asStartElement().getName();
					if (this.desiredName.equals(startElementName)) {
						this.events = new ArrayList<>();
						this.barrier = this.elementDepth;
					}
				}
				this.elementDepth++;
			}
			if (this.elementDepth > this.barrier) {
				this.events.add(event);
			}
			if (event.isEndElement()) {
				this.elementDepth--;
				if (this.elementDepth == this.barrier) {
					this.barrier = Integer.MAX_VALUE;
					return Mono.just(this.events);
				}
			}
			return Mono.empty();
		}
