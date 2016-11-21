package io.lineage.example.helloworld;

import io.lineage.BaseSolver;
import io.lineage.Encoder;
import io.lineage.GAExecutionContext;
import io.lineage.Population;
import io.lineage.core.StringDecoder;
import io.lineage.core.StringEncoder;
import io.lineage.solver.IterationSolver;
import io.lineage.solver.Solver;

import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args)
    {
        solve();
    }

    private static void solve()
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();

        final StringDecoder decoder = new StringDecoder();
        final StringEncoder encoder = new StringEncoder();

        context.setFitnessSelector(new StringFitnessSelector());
        context.setEncoder(encoder);
        context.setDecoder(decoder);
        context.setSolutions(createSolution());

        final IterationSolver.Builder builder = IterationSolver.builder().iterations(2);
        final Solver solver = builder.build();

        if (false)
        {
            final Population pop = createRandomPopulation(10);

            BaseSolver.determineFitness(pop);

            System.out.println();

            pop.dump(" * Initial Population Scores **");

            final Population evolve1 = solver.evolve(pop);
            evolve1.dump(" * After Initial Population Scores **");
            System.out.println(" ----------------  ");

            System.out.println("A evolve1 " + evolve1.getFittest());

            final Population evolve2 = solver.evolve(evolve1);
            System.out.println("A evolve2 " + evolve2.getFittest());

            final Population evolve3 = solver.evolve(evolve2);
            System.out.println("A evolve3 " + evolve3.getFittest());

            System.out.println("Final  Score");
            System.out.println("B evolve1 " + evolve1.getFittest());
            System.out.println("B evolve2 " + evolve2.getFittest());
            System.out.println("B evolve3 " + evolve3.getFittest());

        }

        final Population foundSolutions = solver.findSolution(createRandomPopulation(10));
        LOGGER.info("Best solution count : {}",foundSolutions.size());

        foundSolutions.forEach((c) -> {
            final String decode = decoder.decode(c);
            LOGGER.info("solution : {}",decode);
        });

    }

    private static Population createSolution()
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final Encoder<String> encoder = context.getEncoder();
        return new Population(Arrays.asList(encoder.encode("Hello World")));
    }

    private static Population createRandomPopulation(final int tickets)
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final Encoder<String> encoder = context.getEncoder();

        final Population population = new Population();

        final RandomString rs = new RandomString("Hell".length());
        for (int i = 0; i < tickets; ++i)
        {
            final String value = rs.nextString();
            population.add(encoder.encode(value));
        }

        return population;
    }

    public static class RandomString
    {
        private static final char[] symbols;

        static
        {
            final StringBuilder tmp = new StringBuilder();
            for (char ch = '0'; ch <= '9'; ++ch)
                tmp.append(ch);
            for (char ch = 'a'; ch <= 'z'; ++ch)
                tmp.append(ch);

            symbols = tmp.toString().toCharArray();
        }

        private final Random random = new Random();

        private final char[] buf;

        public RandomString(final int length)
        {
            if (length < 1)
                throw new IllegalArgumentException("length < 1: " + length);
            buf = new char[length];
        }

        public String nextString()
        {
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = symbols[random.nextInt(symbols.length)];
            return new String(buf);
        }
    }
}
