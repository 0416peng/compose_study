# compose学习的笔记

### 基础控件

#### 1.Text

Text的作用是显示文字，类似view中的TextView。以下是Text的基础使用。

![](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521174400262.png)

当然，我们可以通过其他api来改变我们的text。比如通过color和fontSize改变颜色和大小

![image-20250521174834344](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521174834344.png)

![image-20250521174852908](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521174852908.png)

以下是text的参数列表

![image-20250521174938229](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521174938229.png)

#### 2.Button

Button也就是view中的Button。这是Button的常规用法。当按钮被点击时，就会执行onClick参数中的内容。![image-20250521175519683](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521175519683.png)

![image-20250521175653826](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521175653826.png)

这上面没有任何的文字，但是正如我们在view中的button一样，我们也可以为它添加文字。将Text放在Button的闭包中。

![image-20250521175914438](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521175914438.png)

![image-20250521175929443](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20250521175929443.png)

#### 3.TextField

TextField对应的是View中的EditText。