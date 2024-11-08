import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

csv_file_path_without_hunger = './without-hunger.csv'
csv_file_path_naive = './naive.csv'

df_naive = pd.read_csv(csv_file_path_naive, header=None, names=['Type', 'Portion', 'WaitTime'])
df_without_hunger = pd.read_csv(csv_file_path_without_hunger, header=None, names=['Type', 'Portion', 'WaitTime'])

fig, axs = plt.subplots(1, 2)
fig.set_size_inches(14, 7)

sns.scatterplot(data=df_naive, x='Portion', y='WaitTime', hue='Type', marker='o', ax=axs[0])
axs[0].set_title('Naive Method')
axs[0].set_xlabel('Portion Size')
axs[0].set_ylabel('Waiting Time (nanoseconds)')
axs[0].tick_params(axis='x', rotation=45)
axs[0].grid(True, which="both", linestyle='--', linewidth=0.5)
axs[0].legend(title='Type')

sns.scatterplot(data=df_without_hunger, x='Portion', y='WaitTime', hue='Type', marker='o', ax=axs[1])
axs[1].set_title('Without Hunger Method')
axs[1].set_xlabel('Portion Size')
axs[1].set_ylabel('Waiting Time (nanoseconds)')
axs[1].tick_params(axis='x', rotation=45)
axs[1].grid(True, which="both", linestyle='--', linewidth=0.5)
axs[1].legend(title='Type')

plt.tight_layout()
plt.show()
