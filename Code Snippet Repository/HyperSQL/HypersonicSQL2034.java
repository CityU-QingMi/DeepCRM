        void cancelAllTasks() {

            Object[] oldHeap;
            int      oldCount;

            synchronized (this) {
                oldHeap  = this.heap;
                oldCount = this.count;

                // 1 instead of 0 to avoid unintended aoob exceptions
                this.heap  = new Object[1];
                this.count = 0;
            }

            for (int i = 0; i < oldCount; i++) {
                ((Task) oldHeap[i]).cancelled = true;
            }
        }
