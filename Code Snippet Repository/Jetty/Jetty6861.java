        public void assertTransitions(ConnectionState ...states)
        {
            assertThat("Transitions.count",transitions.size(),is(states.length));
            if (states.length > 0)
            {
                int len = states.length;
                for (int i = 0; i < len; i++)
                {
                    assertThat("Transitions[" + i + "]",transitions.get(i),is(states[i]));
                }
            }
        }
