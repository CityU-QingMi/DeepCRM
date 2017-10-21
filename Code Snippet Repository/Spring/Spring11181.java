		@Override
		public void accept(SynchronousSink<LineInfo> sink) {
			if (this.scanner.hasNext()) {
				String line = this.scanner.nextLine();
				LineInfo current = new LineInfo(line, this.previous);
				sink.next(current);
				this.previous = current;
			}
			else {
				sink.complete();
			}
		}
