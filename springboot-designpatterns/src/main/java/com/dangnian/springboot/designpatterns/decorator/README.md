通过建立子类完成对一个类的扩展
比如奶茶
奶茶+波霸
奶茶+波霸+椰果
奶茶+波霸+椰果+红豆
奶茶+波霸+椰果+红豆+糖
奶茶+波霸+椰果+红豆+糖+冰

如果不同的方案都设计为一个类会导致类呈指数倍增长
使用装饰器模式，每个装饰器子负责单一类逻辑的处理
比如是否加冰 是否加糖 是否加红豆 每个都设计为一个装饰器装饰原始的奶茶