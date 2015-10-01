package io.lineage;

import io.lineage.Chromosome.Decoder;
import io.lineage.Chromosome.Encoder;

import java.util.Collections;
import java.util.List;


/**
 * Context for executing GA algorithm
 */
public class GAExecutionContext
{
    private static final ThreadLocal<GAExecutionContext> storage = new ThreadLocal<GAExecutionContext>();

    /**
     * Default population size when generating
     */
    private final int populationSize = 100;

    /**
     * This is the crossover rate
     */
    public final float crossoverRate = 0.7f;

    /**
     * Mutation rate 
     */
    public final float mutationRate = 0.01f;

    /**
     * Encoder used for encoding Objects into Chromos
     */
    private Encoder<?> encoder;

    /**
     * Decored used for decoding bitstream into Chromosomes
     */
    private Decoder<?> decoder;

    private FitnessSelector fitnessSelector;

    private ChromosomeSelector chromosomeSelector;

    private CrossoverOperator crossoverOperator;

    /**
     * This represents list of all acceptable solutions
     */
    private List<Chromosome> solutions;

    private boolean timedIteration;

    /**
     * How long will the GA run
     */
    private long timedIterationTime = 5;

    private int iterationCount = 100;

    private double acceptableFitnessScore;

    static
    {
        bootstrap();
    }

    private static void bootstrap()
    {
        final GAExecutionContext context = new GAExecutionContext();
        context.chromosomeSelector = new RouletteChromosomeSelector();
        context.crossoverOperator = new SplicingCrossoverOperator();
        context.solutions = Collections.emptyList();

        storage.set(context);
    }

    /**
     * Get Current {@link GAExecutionContext} this method will throw
     * {@link IllegalStateException} if the context have not
     * yet been created.
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
        return (Encoder<T>) encoder;
    }

    /**
     * @param encoder the encoder to set
     */
    public void setEncoder(final Encoder<?> encoder)
    {
        this.encoder = encoder;
    }

    /**
     * @return the decoder
     */
    @SuppressWarnings("unchecked")
    public <T> Decoder<T> getDecoder()
    {
        return (Decoder<T>) decoder;
    }

    /**
     * @param decoder the decoder to set
     */
    public void setDecoder(final Decoder<?> decoder)
    {
        this.decoder = decoder;
    }

    /**
     * @return the populationSize
     */
    public int getPopulationSize()
    {
        return populationSize;
    }

    /**
     * @return the fitnessSelector
     */
    public FitnessSelector getFitnessSelector()
    {
        return fitnessSelector;
    }

    /**
     * @param fitnessSelector the fitnessSelector to set
     */
    public void setFitnessSelector(final FitnessSelector fitnessSelector)
    {
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
     * @return the solutions
     */
    public List<Chromosome> getSolutions()
    {
        return solutions;
    }

    /**
     * @param solutions the solutions to set
     */
    public void setSolutions(final List<Chromosome> solutions)
    {
        this.solutions = solutions;
    }

    /**
     * @param chromosomeSelector the chromosomeSelector to set
     */
    public void setChromosomeSelector(final ChromosomeSelector chromosomeSelector)
    {
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
     * @param crossoverOperator the crossoverOperator to set
     */
    public void setCrossoverOperator(final CrossoverOperator crossoverOperator)
    {
        this.crossoverOperator = crossoverOperator;
    }

    /**
     * @return the timedIteration
     */
    public boolean isTimedIteration()
    {
        return timedIteration;
    }

    /**
     * @param timedIteration the timedIteration to set
     */
    public void setTimedIteration(final boolean timedIteration)
    {
        this.timedIteration = timedIteration;
    }

    /**
     * @return the timedIterationTime
     */
    public long getTimedIterationTime()
    {
        return timedIterationTime;
    }

    /**
     * @param timedIterationTime the timedIterationTime to set
     */
    public void setTimedIterationTime(final long timedIterationTime)
    {
        this.timedIterationTime = timedIterationTime;
    }

    public int getIterationCount()
    {
        return iterationCount;
    }

    /**
     * @param iterationCount the iterationCount to set
     */
    public void setIterationCount(final int iterationCount)
    {
        this.iterationCount = iterationCount;
    }

    /**
     * @return the acceptableFitnessScore
     */
    public double getAcceptableFitnessScore()
    {
        return acceptableFitnessScore;
    }

    /**
     * @param acceptableFitnessScore the acceptableFitnessScore to set
     */
    public void setAcceptableFitnessScore(final double acceptableFitnessScore)
    {
        this.acceptableFitnessScore = acceptableFitnessScore;
    }
}
