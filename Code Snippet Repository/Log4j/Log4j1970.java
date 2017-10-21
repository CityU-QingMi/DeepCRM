            @Override
            public void run() {
                for (; ; ) {
                    try {
                        if (Objects.equals(queue.take(), STOP)) {
                            break;
                        }
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
