package io.lineage.test.validator;

import io.lineage.BaseSolver;
import io.lineage.Decoder;
import io.lineage.Encoder;
import io.lineage.GAExecutionContext;
import io.lineage.Population;
import io.lineage.solver.IterationSolver;
import io.lineage.solver.Solver;

import java.util.Arrays;

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

        final Decoder<FunctionParams> decoder = new ValidatorDecoder();
        final Encoder<FunctionParams> encoder = new ValidatorEncoder();

        context.setFitnessSelector(new ValidatorFitnessSelector());
        context.setEncoder(encoder);
        context.setDecoder(decoder);
        context.setSolutions(createSolution());

        final IterationSolver.Builder builder = IterationSolver.builder().iterations(2);
        final Solver solver = builder.build();

        if (true)
        {
            final Population pop = createPopulation();
            BaseSolver.determineFitness(pop);
            pop.dump(" * Initial Population Scores **");

            final Population pop1 = solver.evolve(pop);
            pop1.dump(" * pop1 Population Scores **");

            final Population pop2 = solver.evolve(pop1);
            pop2.dump(" * pop2 Population Scores **");

            
            /*
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
            */
            
        }

        /*
        final Population foundSolutions = solver.findSolution(createRandomPopulation(10));
        LOGGER.info("Best solution count : {}",foundSolutions.size());

        foundSolutions.forEach((c) -> {
            final FunctionParams decode = decoder.decode(c);
            LOGGER.info("solution : {}",decode);
        });
         */
    }

    private static Population createPopulation()
    {
        /*
            x1 = 6 5 4 1 3 5 3 2
            x2 = 8 7 1 2 6 6 0 1
            x3 = 2 3 9 2 1 2 8 5
            x4 = 4 1 8 5 2 0 9 4
         */

        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final Encoder<FunctionParams> encoder = context.getEncoder();
        final Population population = new Population();
        
        population.add(encoder.encode(new FunctionParams(6,5,4,1,3,5,3,2)));
        population.add(encoder.encode(new FunctionParams(8,7,1,2,6,6,0,1)));
        population.add(encoder.encode(new FunctionParams(2,3,9,2,1,2,8,5)));
        population.add(encoder.encode(new FunctionParams(4,1,8,5,2,0,9,4)));

        return population;
    }

    private static Population createSolution()
    {
        // xoptimal = 9 9 0 0 9 9 0 0 
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final Encoder<FunctionParams> encoder = context.getEncoder();
        return new Population(Arrays.asList(encoder.encode(new FunctionParams(9,9,0,0,9,9,0,0))));
    }
   
}
