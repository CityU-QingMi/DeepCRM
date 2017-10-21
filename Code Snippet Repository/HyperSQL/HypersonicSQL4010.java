    private void processElse() throws PreprocessorException {
        switch(state) {
            case CONDITION_NONE : {
                throw new PreprocessorException("Unexpected #else"); // NOI18N
            }
            case CONDITION_ARMED : {
                this.state = CONDITION_IN_TRUE;

                break;
            }
            case CONDITION_IN_TRUE : {
                this.state = CONDITION_TRIGGERED;

                break;
            }
        }
    }
