public abstract  class Operation {


    /**
     * 简单工厂模式，用户不用考虑子类的创建类型，由工厂方法自行判读去生成对应的子类
     *
     * */
    public abstract double getResult(double numberA,double numberB);

}
