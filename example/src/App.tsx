import * as React from 'react';
import RNFetchBlob from 'rn-fetch-blob';

import { StyleSheet, View, Button } from 'react-native';
import { untar } from '@webileapps/react-native-untar';

const sampleTarPath5 = 'https://getsamplefiles.com/download/tar/sample-5.tar';

export default function App() {
  const { tarFilePath, destinationPath } = React.useMemo(() => {
    const dirs = RNFetchBlob.fs.dirs;
    const savePath = `${dirs.DocumentDir}/videos/sample-5.tar`;
    return {
      tarFilePath: savePath,
      destinationPath: `${dirs.DocumentDir}/extracted`,
    };
  }, []);
  const downloadFile = async (url: string) => {
    try {
      const response = await RNFetchBlob.config({
        path: tarFilePath,
      }).fetch('GET', url);
      const status = response.info().status;
      if (status === 200) {
        console.log('The file saved to ', response.path());
      } else {
        console.error('Error downloading file', response);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <View style={styles.container}>
      <Button
        title="Download"
        onPress={async () => {
          await downloadFile(sampleTarPath5);
        }}
      />
      <Button
        title="Extract Tar"
        onPress={async () => {
          const result = await untar(tarFilePath, destinationPath);
          console.log(result);
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
