# 葫芦娃说明文档

##本次作业基于所给的图形界面来写的



##设计理念
1.这次要将葫芦娃组和妖怪组放到二维平面上，并且使他们形成对峙的局面，可以通过一个二维数组来实现<br>
2.新增加的爷爷，蝎子精，蛇精，以及众多喽啰生物完全可以以Creature 为接口来实现<br>
3.增加了阵型来进行对峙，这点内容和之前写的区别很大，需要增加些新的内容<br>

##面向对象的概念
1.所有生物都继承Player<br>

##面向对象的机制

###封装
所有的类都进行了封装，只提供供外部调用的接口，比如蝎子精自己布的阵是**private**类型的，
你从外部没法修改，只有投靠的小喽啰变化了，蝎子精才会自己做出调整<br>

###继承
1.子类继承父类，父类的成员变量一般都用**protected**类型，这样可以方便子类的调用，比如Monster的名字和position都是protected类型的，这样蝎子精和蛇精
就可以调用了<br>
2.类还可以继承接口，这样就可以实现多态继承了，不过接口中的变量是没法覆盖的<br>

###多态
多态可以通过继承接口来实现，比如葫芦娃就继承了**Creature**和**Comparable**，实现了多态。接口里的方法函数都是要重写的<br>

##进程
每个生物是一个进程，生物通过一个二维平面来获取各自的信息，当一个生物杀死另一个生物时，就在该生物进程中关闭另一个生物的进程<br>

##对战
两方以一字长蛇阵排开，然后对冲，然后随机杀死对方<br>

