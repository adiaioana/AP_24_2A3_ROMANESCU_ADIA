package org.example.ex12;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class P {
    private double[] coef;
    public P(double...values){
        coef=values;
    }

    public int getPolValue(int x){
        if(coef.length==0)
            return 0;
        return IntStream.range(0, coef.length).map(i-> (int)coef[i]*(int) Math.pow(x,i)).sum();
    }
    public double getPolValueDouble(double x){
        if(coef.length==0)
            return 0;
        return  IntStream.range(0,coef.length).mapToDouble(i->coef[i]*Math.pow(x,i)).sum();
    }

}