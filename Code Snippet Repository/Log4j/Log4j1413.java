        @Override
        public void abbreviate(final String original, final StringBuilder destination) {
            //
            //  all non-terminal patterns are executed once
            //
            int pos = destination.length();
            final int max = pos + original.length();
            final StringBuilder sb = destination.append(original);//new StringBuilder(original);

            for (int i = 0; i < fragments.length - 1 && pos < original.length(); i++) {
                pos = fragments[i].abbreviate(sb, pos);
            }

            //
            //   last pattern in executed repeatedly
            //
            final PatternAbbreviatorFragment terminalFragment = fragments[fragments.length - 1];

            while (pos < max && pos >= 0) {
                pos = terminalFragment.abbreviate(sb, pos);
            }
        }
