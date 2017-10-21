		private void addTask(Runnable task, boolean successTask) {
			Assert.notNull(this.receiptId,
					"To track receipts, set autoReceiptEnabled=true or add 'receiptId' header");
			synchronized (this) {
				if (this.result != null && this.result == successTask) {
					invoke(Collections.singletonList(task));
				}
				else {
					if (successTask) {
						this.receiptCallbacks.add(task);
					}
					else {
						this.receiptLostCallbacks.add(task);
					}
				}
			}
		}
