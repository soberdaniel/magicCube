import numpy as np
import matplotlib.pyplot as plt


class MagicCube:
    __step_count = 0
    cube = []

    def get_step_count(self):
        print("目前进行了 %s 步操作" % str(self.__step_count))

    def __init__(self):
        for i in range(6):

            self.cube.append([])
            for j in range(3):
                self.cube[i].append([])
                for k in range(3):
                    self.cube[i][j].append(0)
        # cube_one = [[0 for i in range(3)] for i in range(3)]
        # for i in range(6):
        #     temp = cube_one[:]
        #     temp = temp[:]
        #     cube.append(temp)

    def initial(self):
        fp = open('cube_line.txt', 'r')
        line = fp.readline()
        for i in range(6):
            print("----------正在输入第 %s 面数据----------" % str(i+1))
            for j in range(3):
                print("正在读取 %s 列数据" % str(j+1))
                for k in range(3):
                    self.cube[i][j][k] = line[k]
                line = fp.readline()
        print(self.cube)

    def get_cube(self):
        global cube
        for i in range(6):
            print("第 %s 面情况" % str(i+1))
            print(self.cube[i])

    # 整体向下转
    def change_base_to_1(self):
        self.__step_count += 1
        print('整体向下转')
        # 面转换
        temp = []
        for i in range(3):
            temp.append([])
            for j in range(3):
                temp[i].append(self.cube[0][i][j])
        for i in range(3):
            for j in range(3):
                self.cube[0][i][j] = self.cube[1][i][j]
        for i in range(3):
            for j in range(3):
                self.cube[1][i][j] = self.cube[5][2-i][2-j]
        for i in range(3):
            for j in range(3):
                self.cube[5][i][j] = self.cube[4][2-i][2-j]
        self.cube[4] = temp
        # 面旋转
        for i in range(3):
            temp = self.cube[3][i][0]
            self.cube[3][i][0] = self.cube[3][i][2]
            self.cube[3][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[3][i][j]
                self.cube[3][i][j] = self.cube[3][j][i]
                self.cube[3][j][i] = temp

        for i in range(3):
            temp = self.cube[2][0][i]
            self.cube[2][0][i] = self.cube[2][2][i]
            self.cube[2][2][i] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[2][i][j]
                self.cube[2][i][j] = self.cube[2][j][i]
                self.cube[2][j][i] = temp

    # 整体向右转
    def change_base_to_2(self):
        self.__step_count += 1
        print("整体向右转")
        # 面转换
        temp = []
        for i in range(3):
            temp.append([])
            for j in range(3):
                temp[i].append(self.cube[0][i][j])
        for i in range(3):
            for j in range(3):
                self.cube[0][i][j] = self.cube[2][i][j]
        for i in range(3):
            for j in range(3):
                self.cube[2][i][j] = self.cube[5][i][j]
        for i in range(3):
            for j in range(3):
                self.cube[5][i][j] = self.cube[3][i][j]
        self.cube[3] = temp
        # 面旋转
        for i in range(3):
            temp = self.cube[1][i][0]
            self.cube[1][i][0] = self.cube[1][i][2]
            self.cube[1][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[1][i][j]
                self.cube[1][i][j] = self.cube[1][j][i]
                self.cube[1][j][i] = temp

        for i in range(3):
            temp = self.cube[4][0][i]
            self.cube[4][0][i] = self.cube[4][2][i]
            self.cube[4][2][i] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[4][i][j]
                self.cube[4][i][j] = self.cube[4][j][i]
                self.cube[4][j][i] = temp

    # 整体向左转
    def change_base_to_3(self):
        self.__step_count += 1
        print("整体向左转")
        # 面转换
        temp = []
        for i in range(3):
            temp.append([])
            for j in range(3):
                temp[i].append(self.cube[0][i][j])
        for i in range(3):
            for j in range(3):
                self.cube[0][i][j] = self.cube[3][i][j]
        for i in range(3):
            for j in range(3):
                self.cube[3][i][j] = self.cube[5][i][j]
        for i in range(3):
            for j in range(3):
                self.cube[5][i][j] = self.cube[2][i][j]
        self.cube[2] = temp
        # 面旋转
        for i in range(3):
            temp = self.cube[4][i][0]
            self.cube[4][i][0] = self.cube[4][i][2]
            self.cube[4][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[4][i][j]
                self.cube[4][i][j] = self.cube[4][j][i]
                self.cube[4][j][i] = temp

        for i in range(3):
            temp = self.cube[1][0][i]
            self.cube[1][0][i] = self.cube[1][2][i]
            self.cube[1][2][i] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[1][i][j]
                self.cube[1][i][j] = self.cube[1][j][i]
                self.cube[1][j][i] = temp

    # 整体向上转
    def change_base_to_4(self):
        self.__step_count += 1
        print("整体向上转")
        # 面转换
        temp = []
        for i in range(3):
            temp.append([])
            for j in range(3):
                temp[i].append(self.cube[0][i][j])
        for i in range(3):
            for j in range(3):
                self.cube[0][i][j] = self.cube[4][i][j]
        for i in range(3):
            for j in range(3):
                self.cube[4][i][j] = self.cube[5][2 - i][2 - j]
        for i in range(3):
            for j in range(3):
                self.cube[5][i][j] = self.cube[1][2 - i][2 - j]
        self.cube[1] = temp
        # 面旋转
        for i in range(3):
            temp = self.cube[2][i][0]
            self.cube[2][i][0] = self.cube[2][i][2]
            self.cube[2][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[2][i][j]
                self.cube[2][i][j] = self.cube[2][j][i]
                self.cube[2][j][i] = temp

        for i in range(3):
            temp = self.cube[3][0][i]
            self.cube[3][0][i] = self.cube[3][2][i]
            self.cube[3][2][i] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[3][i][j]
                self.cube[3][i][j] = self.cube[3][j][i]
                self.cube[3][j][i] = temp

    # 整体向后转
    def change_base_to_5(self):
        self.__step_count += 1
        print("整体向后转")
        # 面转换
        temp = []
        for i in range(3):
            temp.append([])
            for j in range(3):
                temp[i].append(self.cube[0][i][j])
        for i in range(3):
            for j in range(3):
                self.cube[0][i][j] = self.cube[5][i][j]
        self.cube[5] = temp

        temp = []
        for i in range(3):
            temp.append([])
            for j in range(3):
                temp[i].append(self.cube[3][i][j])
        for i in range(3):
            for j in range(3):
                self.cube[3][i][j] = self.cube[2][i][j]
        self.cube[2] = temp
        # 面旋转
        for i in range(3):
            temp = self.cube[4][i][0]
            self.cube[4][i][0] = self.cube[4][i][2]
            self.cube[4][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[4][i][j]
                self.cube[4][i][j] = self.cube[4][j][i]
                self.cube[4][j][i] = temp
        for i in range(3):
            temp = self.cube[4][i][0]
            self.cube[4][i][0] = self.cube[4][i][2]
            self.cube[4][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[4][i][j]
                self.cube[4][i][j] = self.cube[4][j][i]
                self.cube[4][j][i] = temp

        for i in range(3):
            temp = self.cube[1][0][i]
            self.cube[1][0][i] = self.cube[1][2][i]
            self.cube[1][2][i] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[1][i][j]
                self.cube[1][i][j] = self.cube[1][j][i]
                self.cube[1][j][i] = temp
        for i in range(3):
            temp = self.cube[1][0][i]
            self.cube[1][0][i] = self.cube[1][2][i]
            self.cube[1][2][i] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[1][i][j]
                self.cube[1][i][j] = self.cube[1][j][i]
                self.cube[1][j][i] = temp

    # 图形化展示
    def get_cube_visualization(self):


        # 把画布分为三行五列，并设置figure标题
        fig, axs = plt.subplots(2, 3, figsize=(15, 10), sharex=True, sharey=False)
        fig.suptitle('THE INFORMATION OF CUBE')

        # 选中第三行第三个画出散点图
        for i in range(6):
            row = int(i / 3)
            now = int(i % 3)

            axs[row][now].set_title(i+1)
            x = [1, 2, 3]
            for j in range(3):
                for k in range(3):
                    if self.cube[i][k][j] == 'o':
                        axs[row][now].bar(j+1, 3-k, color='darkorange', width=1)
                    else:
                        axs[row][now].bar(j+1, 3-k, color=self.cube[i][k][j], width=1)
            axs[row][now].set_xlim(0.5, 3.5)
            axs[row][now].set_ylim(0, 3)
            axs[row][now].axis('off')
        plt.show()

    # 旋转操作
    # 旋转时，影响5个面

    # 上面右旋
    def right(self):
        self.__step_count += 1
        print("前面顶层右旋")
        temp = self.cube[0][0][:]
        self.cube[0][0] = self.cube[2][0]
        self.cube[2][0] = self.cube[5][0]
        self.cube[5][0] = self.cube[3][0]
        self.cube[3][0] = temp
        for i in range(3):
            temp = self.cube[1][i][0]
            self.cube[1][i][0] = self.cube[1][i][2]
            self.cube[1][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[1][i][j]
                self.cube[1][i][j] = self.cube[1][j][i]
                self.cube[1][j][i] = temp

    # 下面左旋
    def left(self):
        self.__step_count += 1
        print("前面底层左旋")
        temp = self.cube[0][2][:]
        self.cube[0][2] = self.cube[3][2]
        self.cube[3][2] = self.cube[5][2]
        self.cube[5][2] = self.cube[2][2]
        self.cube[2][2] = temp
        for i in range(3):
            temp = self.cube[4][i][0]
            self.cube[4][i][0] = self.cube[4][i][2]
            self.cube[4][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[4][i][j]
                self.cube[4][i][j] = self.cube[4][j][i]
                self.cube[4][j][i] = temp

    # 右面下旋
    def down(self):
        self.__step_count += 1
        print("前面右侧下旋")
        temp = [self.cube[0][i][2] for i in range(3)]
        for i in range(3):
            self.cube[0][i][2] = self.cube[1][i][2]
            self.cube[1][i][2] = self.cube[5][2-i][0]
            self.cube[5][2 - i][0] = self.cube[4][i][2]
            self.cube[4][i][2] = temp[i]
        for i in range(3):
            temp = self.cube[3][i][0]
            self.cube[3][i][0] = self.cube[3][i][2]
            self.cube[3][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[3][i][j]
                self.cube[3][i][j] = self.cube[3][j][i]
                self.cube[3][j][i] = temp

    # 左面上旋
    def up(self):
        self.__step_count += 1
        print("前面左侧上旋")
        temp = [self.cube[0][i][0] for i in range(3)]
        for i in range(3):
            self.cube[0][i][0] = self.cube[4][i][0]
            self.cube[4][i][0] = self.cube[5][2-i][2]
            self.cube[5][2 - i][2] = self.cube[1][i][0]
            self.cube[1][i][0] = temp[i]
        for i in range(3):
            temp = self.cube[2][i][0]
            self.cube[2][i][0] = self.cube[2][i][2]
            self.cube[2][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[2][i][j]
                self.cube[2][i][j] = self.cube[2][j][i]
                self.cube[2][j][i] = temp

    # 前面逆时针
    def front_clockwise(self):
        self.__step_count += 1
        print("前面逆时针旋转")
        for i in range(3):
            temp = self.cube[0][i][0]
            self.cube[0][i][0] = self.cube[0][i][2]
            self.cube[0][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[0][i][j]
                self.cube[0][i][j] = self.cube[0][j][i]
                self.cube[0][j][i] = temp
        temp = [self.cube[2][i][2] for i in range(3)]
        for i in range(3):
            self.cube[2][i][2] = self.cube[1][2][2-i]
            self.cube[1][2][2 - i] = self.cube[3][2-i][0]
            self.cube[3][2 - i][0] = self.cube[4][0][i]
            self.cube[4][0][i] = temp[i]

    # 后面顺时针
    def behind_clockwise(self):
        self.__step_count += 1
        print("后面顺时针旋转")
        for i in range(3):
            temp = self.cube[5][i][0]
            self.cube[5][i][0] = self.cube[5][i][2]
            self.cube[5][i][2] = temp
        for i in range(3):
            for j in range(i, 3):
                temp = self.cube[5][i][j]
                self.cube[5][i][j] = self.cube[5][j][i]
                self.cube[5][j][i] = temp
        temp = [self.cube[3][i][2] for i in range(3)]
        for i in range(3):
            self.cube[3][i][2] = self.cube[1][0][i]
            self.cube[1][0][i] = self.cube[2][2-i][0]
            self.cube[2][2 - i][0] = self.cube[4][2][i]
            self.cube[4][2][i] = temp[i]

    # 处理两个棱
    def deal_edge(self):
        #
        self.down()
        self.down()
        self.down()
        #
        self.right()
        self.right()
        self.right()
        #
        self.down()
        #
        self.right()
        self.right()
        self.right()
        #
        self.down()
        self.down()
        self.down()
        #
        self.right()
        self.right()
        #
        self.down()
        #

    # 处理顶面的角
    def deal_top_angle(self):
        self.down()
        self.right()
        self.right()
        self.right()
        self.up()
        self.up()
        self.up()
        self.right()
        self.down()
        self.down()
        self.down()
        self.right()
        self.right()
        self.right()
        self.up()
        self.right()

    # 复原第一层
    def deal_first(self):
        while self.cube[0][0][1] != self.cube[0][1][1] or self.cube[0][1][0] != self.cube[0][1][1] or \
                self.cube[0][1][2] != self.cube[0][1][1] or self.cube[0][2][1] != self.cube[0][1][1]:
            # 处理上面
            if self.cube[1][0][1] == self.cube[0][1][1]:
                while self.cube[0][0][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.right()
            if self.cube[1][1][0] == self.cube[0][1][1]:
                while self.cube[0][1][0] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.up()
                self.up()
                self.up()
            if self.cube[1][2][1] == self.cube[0][1][1]:
                while self.cube[0][0][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.right()
            if self.cube[1][1][2] == self.cube[0][1][1]:
                while self.cube[0][1][0] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.down()

            # 处理左面
            if self.cube[2][1][0] == self.cube[0][1][1]:
                while self.cube[0][1][0] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.up()
                self.up()
                self.up()
            if self.cube[2][0][1] == self.cube[0][1][1]:
                while self.cube[0][0][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.right()
            if self.cube[2][1][2] == self.cube[0][1][1]:
                while self.cube[0][1][0] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.up()
                self.up()
                self.up()
            if self.cube[2][2][1] == self.cube[0][1][1]:
                while self.cube[0][2][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.left()
                self.left()
                self.left()

            # 处理右面
            if self.cube[3][0][1] == self.cube[0][1][1]:
                while self.cube[0][0][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.right()
                self.right()
                self.right()
            if self.cube[3][1][0] == self.cube[0][1][1]:
                while self.cube[0][1][2] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.down()
            if self.cube[3][2][1] == self.cube[0][1][1]:
                while self.cube[0][2][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.left()
            if self.cube[3][1][2] == self.cube[0][1][1]:
                while self.cube[0][1][2] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.down()

            # 处理下面
            if self.cube[4][0][1] == self.cube[0][1][1]:
                while self.cube[0][2][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.left()
            if self.cube[4][1][0] == self.cube[0][1][1]:
                while self.cube[0][1][0] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.up()
            if self.cube[4][2][1] == self.cube[0][1][1]:
                while self.cube[0][2][1] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.left()
            if self.cube[4][1][2] == self.cube[0][1][1]:
                while self.cube[0][1][2] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.down()
                self.down()
                self.down()

            # 处理后面
            if self.cube[5][0][1] == self.cube[0][1][1]:
                self.behind_clockwise()
            if self.cube[5][2][1] == self.cube[0][1][1]:
                self.behind_clockwise()
            if self.cube[5][1][0] == self.cube[0][1][1]:
                while self.cube[0][1][2] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.down()
                self.down()
            if self.cube[5][1][2] == self.cube[0][1][1]:
                while self.cube[0][1][0] == self.cube[0][1][1]:
                    self.front_clockwise()
                self.up()
                self.up()

        # 处理棱
        print("处理第一层的棱")
        self.change_base_to_4()
        while self.cube[0][0][1] != self.cube[0][1][1]:
            self.right()
        while self.cube[0][0][1] != self.cube[0][1][1] or self.cube[2][1][1] != self.cube[2][0][1] or  \
            self.cube[3][0][1] != self.cube[3][1][1] or self.cube[5][0][1] != self.cube[5][1][1]:
            if self.cube[3][0][1] == self.cube[3][1][1]:
                self.change_base_to_2()
                self.deal_edge()
                self.right()
                self.right()
                self.right()
            elif self.cube[2][0][1] == self.cube[2][1][1]:
                self.change_base_to_5()
                self.deal_edge()
                self.right()
                self.right()
                self.right()
            else:
                self.deal_edge()

        # 处理角
        print("处理第一层的角")
        while 1:
            if self.cube[0][0][0] == self.cube[0][0][1] == self.cube[0][0][2] and self.cube[2][0][0] == self.cube[2][0][1] == self.cube[2][0][2] and self.cube[3][0][0] == self.cube[3][0][1] == self.cube[3][0][2] and self.cube[5][0][0] == self.cube[5][0][1] == self.cube[5][0][2]:
                break
            else:
                if self.cube[0][0][2] != self.cube[0][1][1] or self.cube[3][0][0] != self.cube[3][1][1]:
                    if self.cube[0][2][2] == self.cube[1][1][1] and self.cube[4][0][2] == self.cube[0][1][1]:
                        self.left()
                    self.down()
                    times = 0
                    while times < 3:
                        if self.cube[0][2][2] == self.cube[1][1][1] and self.cube[4][0][2] == self.cube[0][1][1]:
                            break
                        self.left()
                        times += 1
                    self.down()
                    self.down()
                    self.down()
                if self.cube[0][0][0] != self.cube[0][1][1] or self.cube[2][0][2] != self.cube[2][1][1]:
                    if self.cube[0][2][0] == self.cube[1][1][1] and self.cube[4][0][0] == self.cube[0][1][1]:
                        self.left()
                        self.left()
                        self.left()
                    self.up()
                    self.up()
                    self.up()
                    times = 0
                    while times < 3:
                        if self.cube[0][2][2] == self.cube[1][1][1] and self.cube[4][0][2] == self.cube[0][1][1]:
                            break
                        self.left()
                        times += 1
                    self.up()
                self.change_base_to_2()

    # 复原第二层
    def deal_second(self):
        print("处理第二层")
        self.change_base_to_1()
        self.change_base_to_1()
        while 1:
            self.change_base_to_2()
            if self.cube[0][1][0] == self.cube[0][1][1] == self.cube[0][1][2] and self.cube[2][1][0] == self.cube[2][1][1] == self.cube[2][1][2] and self.cube[3][1][0] == self.cube[3][1][1] == self.cube[3][1][2] and self.cube[5][1][0] == self.cube[5][1][1] == self.cube[5][1][2]:
                break
            else:
                if self.cube[0][1][0] == self.cube[0][1][1] == self.cube[0][1][2] and self.cube[2][1][1] == self.cube[2][1][2] and self.cube[3][1][0] == self.cube[3][1][1]:
                    continue
                if self.cube[0][1][0] == self.cube[2][1][1] and self.cube[2][1][2] == self.cube[0][1][1]:
                    self.right()
                    self.up()
                    self.right()
                    self.right()
                    self.right()
                    self.up()
                    self.up()
                    self.up()
                    self.right()
                    self.right()
                    self.right()
                    self.front_clockwise()
                    self.front_clockwise()
                    self.front_clockwise()
                    self.right()
                    self.front_clockwise()
                    continue
                elif self.cube[0][1][2] == self.cube[3][1][1] and self.cube[3][1][0] == self.cube[0][1][1]:
                    self.right()
                    self.right()
                    self.right()
                    self.down()
                    self.down()
                    self.down()
                    self.right()
                    self.down()
                    self.right()
                    self.front_clockwise()
                    self.right()
                    self.right()
                    self.right()
                    self.front_clockwise()
                    self.front_clockwise()
                    self.front_clockwise()
                    continue
                times = 0
                while times < 3:
                    if self.cube[0][0][1] == self.cube[0][1][1] and (self.cube[1][2][1] == self.cube[2][1][1] or self.cube[1][2][1] == self.cube[3][1][1]):
                        break
                    self.right()
                    times += 1
                if (self.cube[0][0][1] == self.cube[0][1][1] and self.cube[1][2][1] == self.cube[2][1][1]):
                        # or (self.cube[0][1][0] != self.cube[1][1][1] and self.cube[2][1][2] != self.cube[1][1][1]):
                    self.right()
                    self.up()
                    self.right()
                    self.right()
                    self.right()
                    self.up()
                    self.up()
                    self.up()
                    self.right()
                    self.right()
                    self.right()
                    self.front_clockwise()
                    self.front_clockwise()
                    self.front_clockwise()
                    self.right()
                    self.front_clockwise()
                if (self.cube[0][0][1] == self.cube[0][1][1] and self.cube[1][2][1] == self.cube[3][1][1]):
                        #or (self.cube[0][1][2] != self.cube[1][1][1] and self.cube[3][1][0] != self.cube[1][1][1]):
                    self.right()
                    self.right()
                    self.right()
                    self.down()
                    self.down()
                    self.down()
                    self.right()
                    self.down()
                    self.right()
                    self.front_clockwise()
                    self.right()
                    self.right()
                    self.right()
                    self.front_clockwise()
                    self.front_clockwise()
                    self.front_clockwise()

    # 处理第三层的角
    def deal_thread_angle(self):
        self.up()
        self.up()
        self.up()
        self.front_clockwise()
        self.front_clockwise()
        self.front_clockwise()
        self.deal_top_angle()
        self.front_clockwise()
        self.up()



    # 复原第三层
    def deal_thread(self):
        # 构造顶面十字
        print("# 构造顶面十字")
        while 1:
            if self.cube[1][0][1] == self.cube[1][1][0] == self.cube[1][1][1] == self.cube[1][1][2] == self.cube[1][2][1]:
                break
            else:
                if (self.cube[1][0][1] == self.cube[1][1][0] == self.cube[1][1][1]) or (self.cube[1][1][0] == self.cube[1][1][1] == self.cube[1][1][2]) or (self.cube[1][1][1] != self.cube[1][1][0] and self.cube[1][1][1] != self.cube[1][1][2] and self.cube[1][1][1] != self.cube[1][0][1] and self.cube[1][1][1] != self.cube[1][2][1]):
                    self.front_clockwise()
                    self.front_clockwise()
                    self.front_clockwise()
                    self.down()
                    self.down()
                    self.down()
                    self.right()
                    self.right()
                    self.right()
                    self.down()
                    self.right()
                    self.front_clockwise()
                else:
                    self.right()

        # 处理第三层的棱
        print("# 处理第三层的棱")
        while self.cube[0][0][1] != self.cube[0][1][1]:
            self.right()
        while self.cube[0][0][1] != self.cube[0][1][1] or self.cube[2][1][1] != self.cube[2][0][1] or \
                self.cube[3][0][1] != self.cube[3][1][1] or self.cube[5][0][1] != self.cube[5][1][1]:
            if self.cube[3][0][1] == self.cube[3][1][1]:
                self.change_base_to_2()
                self.deal_edge()
                self.right()
                self.right()
                self.right()
            elif self.cube[2][0][1] == self.cube[2][1][1]:
                self.change_base_to_5()
                self.deal_edge()
                self.right()
                self.right()
                self.right()
            else:
                self.deal_edge()

        # 处理顶层
        print("# 处理顶层")
        before = 0
        while 1:
            count = 0
            for i in range(3):
                for j in range(3):
                    if self.cube[1][i][j] == self.cube[1][1][1]:
                        count += 1
            if count == 9:
                break
            elif count == 5:
                self.deal_top_angle()
            elif count == 6:
                if self.cube[1][0][0] == self.cube[1][0][1] == self.cube[1][1][0] == self.cube[1][1][1] == self.cube[1][1][2] == self.cube[1][2][1]:
                  self.deal_top_angle()
                else:
                    self.change_base_to_2()
            elif count == 7:
                # if (self.cube[1][0][0] == self.cube[1][1][1] == self.cube[1][2][2]) or (self.cube[1][0][2] == self.cube[1][1][1] == self.cube[1][2][0]):
                if count == before:
                    self.change_base_to_2()
                self.deal_top_angle()
            before = count

        # 处理第三层的角
        print("# 处理第三层的角")
        while 1:
            if self.cube[0][0][0] == self.cube[0][0][1] == self.cube[0][0][2] and self.cube[2][0][0] == self.cube[2][0][1] == self.cube[2][0][2] and self.cube[3][0][0] == self.cube[3][0][1] == self.cube[3][0][2] and self.cube[5][0][0] == self.cube[5][0][1] == self.cube[5][0][2]:
                break

            else:
                times = 0
                while times < 4:
                    if self.cube[0][0][1] == self.cube[0][0][2]:
                        self.deal_thread_angle()
                        break
                    else:
                        self.change_base_to_2()
                        times += 1
                if times == 4:
                    self.deal_thread_angle()




cube = MagicCube()
cube.initial()
cube.get_cube_visualization()
cube.deal_first()
cube.deal_second()
cube.deal_thread()
cube.get_step_count()
cube.get_cube_visualization()



