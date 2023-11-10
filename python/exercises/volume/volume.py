import math


def cube(x):
    return x ** 3


def volumeSphere(r):

    volume = (4/3) * math.pi * cube(r)
    return volume

