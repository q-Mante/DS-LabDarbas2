package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.AvlSet;
import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.SortedSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 10)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class FullSet {

        Car[] cars;
        BstSet<Car> carSetBst;
        AvlSet<Car> carSetAvl;

        @Setup(Level.Iteration)
        public void generateElements(BenchmarkParams params) {
            cars = Benchmark.generateElements(Integer.parseInt(params.getParam("elementCount")));
        }

        @Setup(Level.Invocation)
        public void fillCarSet(BenchmarkParams params) {
            carSetBst = new BstSet<>();
            carSetAvl = new AvlSet<>();
            addElements(cars, carSetBst);
            addElements(cars, carSetAvl);
        }
    }

    @Param({"10000", "20000", "40000", "80000", "160000"})
    public int elementCount;

    Car[] cars;

    Car nonCar;

    @Setup(Level.Iteration)
    public void generateElements() {
        cars = generateElements(elementCount);
        nonCar = new Car("a","a",2000,2000, 2000);
    }

    static Car[] generateElements(int count) {
        return new CarsGenerator().generateShuffle(count, 1.0);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void removeBst(FullSet carSet) {
        carSet.carSetBst.remove(nonCar);
        //carSet.carSetBst.remove(carSet.cars[8500]);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void removeAvl(FullSet carSet) {
        carSet.carSetAvl.remove(nonCar);
        //carSet.carSetAvl.remove(carSet.cars[8500]);
    }

    public static void addElements(Car[] carArray, SortedSet<Car> carSet) {
        for (Car car : carArray) {
            carSet.add(car);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
