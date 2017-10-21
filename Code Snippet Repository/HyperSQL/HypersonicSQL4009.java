    private void processElseIf(boolean condition) throws PreprocessorException {
        switch(state) {
            case CONDITION_NONE : {
                throw new PreprocessorException("Unexpected #elif"); // NOI18N
            }
            case CONDITION_ARMED : {
                if (condition) {
                    this.state = CONDITION_IN_TRUE;
                }

                break;
            }
            case CONDITION_IN_TRUE : {
                this.state = CONDITION_TRIGGERED;

                break;
            }
        }
    }
