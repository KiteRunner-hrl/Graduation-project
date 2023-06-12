'''
Author: HuRuiLin
Date: 2023-06-12 14:10:27
LastEditor: HuRuilin
LastEditTime: 2023-06-12 14:10:42
'''
"""
  作者：LCQT
  日期：2023年06月10日20:11
  项目描述：
"""
import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from openpyxl import Workbook
import os

driver = webdriver.Chrome()
driver.maximize_window()
driver.set_page_load_timeout(15)
driver.get('https://search.jd.com/search?keyword=%E7%AC%94%E8%AE%B0%E6%9C%AC%E7%94%B5%E8%84%91&wq=%E7%AC%94%E8%AE%B0%E6%9C%AC%E7%94%B5%E8%84%91&ev=exbrand_%E5%8D%8E%E7%A1%95%EF%BC%88ASUS%EF%BC%89%5E')
driver.execute_script('window.scrollTo(0, document.body.scrollHeight);')  # 滚动屏幕
time.sleep(3)

goods = driver.find_elements(By.XPATH, '//*[@id="J_goodsList"]/ul/li')
print('本页共有商品：', len(goods), '件')

# 创建Excel工作簿
workbook = Workbook()
sheet = workbook.active

# 写入表头
sheet['A1'] = '商品名称'
sheet['B1'] = '价格'

# 写入数据
for index, good in enumerate(goods, start=2):
    try:
        title = good.find_element(By.XPATH, './div/div[3]/a/em').text
        price = good.find_element(By.XPATH, './div/div[2]/strong/i').text
        sheet[f'A{index}'] = title
        sheet[f'B{index}'] = price
        print(title, price)
    except Exception as e:
        print(e)

# 获取桌面路径
desktop_path = os.path.join(os.path.expanduser("~"), "Desktop")

# 保存Excel文件到桌面
file_path = os.path.join(desktop_path, 'goods.xlsx')
workbook.save(file_path)

driver.quit()
