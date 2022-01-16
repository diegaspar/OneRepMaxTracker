package com.diegaspar.onerepmax

class OneRepMaxCalculator {
    fun calculateWithBrzyckiFormula(reps: Float, weight: Float): Float {
        return weight * 36 / (37 - reps)
    }
}