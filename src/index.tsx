import { NativeModules } from 'react-native';

const Untar = NativeModules.Untar;

export function untar(
  tarPath: string,
  destinationPath: string
): Promise<boolean> {
  return Untar.untar(tarPath, destinationPath);
}
