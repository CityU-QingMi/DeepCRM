		void onMessage(String name) {
			for (DeferredResult<Person> deferredResult : this.deferredResults) {
				deferredResult.setResult(new Person(name));
				this.deferredResults.remove(deferredResult);
			}
			for (ListenableFutureTask<Person> futureTask : this.futureTasks) {
				futureTask.run();
				this.futureTasks.remove(futureTask);
			}
		}
