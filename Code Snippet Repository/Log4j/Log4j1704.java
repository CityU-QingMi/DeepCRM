        public static void main(final String[] args) {

            if (args.length != 3) {
                System.out.println("Required arguments 'id', 'count' and 'lock' not provided");
                System.exit(-1);
            }
            final String id = args[0];

            final int count = Integer.parseInt(args[1]);

            if (count <= 0) {
                System.out.println("Invalid count value: " + args[1]);
                System.exit(-1);
            }
            final boolean lock = Boolean.parseBoolean(args[2]);

            final boolean createOnDemand = Boolean.parseBoolean(args[2]);

            // System.out.println("Got arguments " + id + ", " + count + ", " + lock);

            try {
                writer(lock, count, id, createOnDemand, true);
                // thread.sleep(50);

            } catch (final Exception e) {
                Throwables.rethrow(e);
            }

        }
