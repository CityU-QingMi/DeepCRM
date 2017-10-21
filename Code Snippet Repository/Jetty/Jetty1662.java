        @Override
        public Runnable produce()
        {
            while (true)
            {
                Runnable task = processSelected();
                if (task != null)
                    return task;

                Runnable action = nextAction();
                if (action != null)
                    return action;

                update();

                if (!select())
                    return null;
            }
        }
