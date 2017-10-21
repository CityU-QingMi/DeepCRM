		@Override
		public void onComplete() {
			if (this.values.size() > 1 || this.multiValueSource) {
				this.result.setResult(this.values);
			}
			else if (this.values.size() == 1) {
				this.result.setResult(this.values.get(0));
			}
			else {
				this.result.setResult(null);
			}
		}
