package io.lineage;

@FunctionalInterface
public interface AcceptanceEvaluator
{
    /**
     * Evaluate given {@link Chromosome} fitness to see if it will be part of the final result.
     * 
     * @param chromosome
     * @return non null Evaluation
     */
    Evaluation evaluate(final Chromosome chromosome);

    public static class Evaluation
    {
        /**
         * Pre-cached not-accepter object
         */
        public static Evaluation NOT_ACCEPTED = new Evaluation(false,false,null);

        /**
         * This flag controls if we are going to continue evolving after finding a results
         */
        public boolean stopNextCycle;

        /**
         * {@link Chromosome} that was evaluated
         */
        public Chromosome selection;

        /**
         * Indicated is our {@link Chromosome} have been accepted as our solution
         */
        public final boolean accepted;

        public Evaluation(final boolean accepted, final boolean stopNextCycle, final Chromosome selection)
        {
            this.accepted = accepted;
            this.stopNextCycle = stopNextCycle;
            this.selection = selection;
        }

        @Override
        public String toString()
        {
            return String.format("accepted : %s stopNextCycle : %s",accepted,stopNextCycle);
        }
    }
}
