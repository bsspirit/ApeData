package org.ape.data.core.io;

import org.ape.data.core.io.model.CsvMetaInfo;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface DownLoad {
	
	CsvMetaInfo ftpDownLoad(ImportFileType ift);
	CsvMetaInfo SftpDownLoad(ImportFileType ift);

}
