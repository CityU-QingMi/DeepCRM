        private void rewindParameters(int position) {

            Iterator it = parameters.keySet().iterator();

            while (it.hasNext()) {
                int pos = it.nextInt();

                if (pos >= position) {
                    it.remove();
                }
            }
        }
