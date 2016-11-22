package io.lineage;

import static io.lineage.Assert.notNull;
import io.lineage.AcceptanceEvaluator.Evaluation;

import java.util.Comparator;

/**
 * Context for executing GA algorithm
 */
public class GAExecutionContext
{
    private static final ThreadLocal<GAExecutionContext> storage = new ThreadLocal<GAExecutionContext>();

    private final int elitism = 4;

    /**
     * This is the crossover rate
     */
    public final double crossoverRate = 0.7f;

    /**
     * Mutation rate
     */
    public final double mutationRate = 0.001f;

    /**
     * Default acceptance threshold
     */
    public final double defaultSolutionAcceptanceThreashold = .95;

    public final boolean defaultSolutionAcceptanceStop = false;

    /**
     * Encoder used for encoding Objects into Chromosomes
     */
    private Encoder<?> encoder;

    /**
     * Decoder used for decoding bitstream into Chromosomes
     */
    private Decoder<?> decoder;

    private FitnessSelector fitnessSelector;

    private ChromosomeSelector chromosomeSelector;

    private CrossoverOperator crossoverOperator;

    private AcceptanceEvaluator acceptanceEvaluator;

    private final Comparator<Chromosome> fitnessComparator = new FitnessComparator();

    /**
     * Current generation that is being evolved
     */
    int currentGeneration;

    /**
     * Population representing our solution space
     */
    private Population solutions;

    static
    {
        bootstrap();
    }

    private static void bootstrap()
    {
        final GAExecutionContext context = new GAExecutionContext();

        context.chromosomeSelector = new RouletteChromosomeSelector();
        context.crossoverOperator = new SplicingCrossoverOperator();

        // Default
        context.setAcceptanceEvaluator((final Chromosome c) -> {
            
            System.out.println("Chromosome " + c);
            System.out.println("Chromosome fitness " + c.fitness);
            return new Evaluation(c.fitness > context.defaultSolutionAcceptanceThreashold,context.defaultSolutionAcceptanceStop,c);
        });

        storage.set(context);
    }

    /**
     * Get Current {@link GAExecutionContext} this method will throw {@link IllegalStateException} if the context have not yet been created.
     * 
     * @return Current context that is used for execution.
     */
    public static GAExecutionContext currentExecutionContext()
    {
        final GAExecutionContext context = storage.get();
        if (context == null)
            throw new IllegalStateException("Context have not yet been initialized properly");

        return context;
    }

    /**
     * @return the encoder
     */
    @SuppressWarnings("unchecked")
    public <T> Encoder<T> getEncoder()
    {
        return (Encoder<T>)encoder;
    }

    /**
     * @param encoder
     *            the encoder to set
     */
    public void setEncoder(final Encoder<?> encoder)
    {
        notNull(encoder);
        this.encoder = encoder;
    }

    /**
     * @return the decoder
     */
    @SuppressWarnings("unchecked")
    public <T> Decoder<T> getDecoder()
    {
        return (Decoder<T>)decoder;
    }

    /**
     * @param decoder
     *            the decoder to set
     */
    public void setDecoder(final Decoder<?> decoder)
    {
        notNull(decoder);
        this.decoder = decoder;
    }

    /**
     * @return the fitnessSelector
     */
    public FitnessSelector getFitnessSelector()
    {
        return fitnessSelector;
    }

    /**
     * @param fitnessSelector
     *            the fitnessSelector to set
     */
    public void setFitnessSelector(final FitnessSelector fitnessSelector)
    {
        notNull(fitnessSelector);
        this.fitnessSelector = fitnessSelector;
    }

    /**
     * @return the chromosomeSelector
     */
    public ChromosomeSelector getChromosomeSelector()
    {
        return chromosomeSelector;
    }

    /**
     * @param chromosomeSelector
     *            the chromosomeSelector to set
     */
    public void setChromosomeSelector(final ChromosomeSelector chromosomeSelector)
    {
        Assert.notNull(chromosomeSelector);

        this.chromosomeSelector = chromosomeSelector;
    }

    /**
     * @return the crossoverOperator
     */
    public CrossoverOperator getCrossoverOperator()
    {
        return crossoverOperator;
    }

    /**
     * @param crossoverOperator
     *            the crossoverOperator to set
     */
    public void setCrossoverOperator(final CrossoverOperator crossoverOperator)
    {
        this.crossoverOperator = crossoverOperator;
    }

    public int getElitism()
    {
        return elitism;
    }

    public Comparator<Chromosome> getFitnessComparator()
    {
        return fitnessComparator;
    }

    public void setSolutions(final Population population)
    {
        this.solutions = population;
    }

    public Population getSolutions()
    {
        return solutions;
    }

    public AcceptanceEvaluator getAcceptanceEvaluator()
    {
        return acceptanceEvaluator;
    }

    public void setAcceptanceEvaluator(final AcceptanceEvaluator acceptanceEvaluator)
    {
        this.acceptanceEvaluator = acceptanceEvaluator;
    }

}
