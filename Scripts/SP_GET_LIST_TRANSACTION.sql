/* Copyright 2018 Rede S.A.
=============================================
Autor: Jéssica Cruz
Data : 20/07/2018
Empresa : Rede
Descricao: Procedure que retorna lista de transações de um determinado periodo
*/

USE [ECDB007]
GO

IF ( OBJECT_ID('dbo.SP_GET_LIST_TRANSACTION') IS NOT NULL )
   DROP PROCEDURE dbo.SP_GET_LIST_TRANSACTION
GO

CREATE PROC [dbo].[SP_GET_LIST_TRANSACTION]
	@dataInicial as datetime,
	@dataValida as bit,
	@AppKey as integer
AS
BEGIN

	DECLARE @dateSearch as datetime

	IF(@dataValida=1)
		BEGIN
			SET @dateSearch=@dataInicial
		END
	ELSE
		BEGIN
			SET @dateSearch= (SELECT MAX(DTH_FIM_PCM) FROM TBEC_LOG_PCM WHERE COD_TIP_PCM=@AppKey)
		END
	
			SELECT 
			TA.[NUM_REF_TRAN],[NUM_SEQL_UNC_AUT],[NUM_SEQL_UNC_CNFR_PRE_AUT],TA.[NUM_MRCH],TA.[NUM_PDV],[COD_RET_CLI],[COD_STTU_TRAN],[COD_TIP_TRAN],
			[NOM_PORT_CLI],[NUM_ORG_TRAN],[IND_TRAN_RCRR],[DES_DBA],[COD_IATA],[VAL_TX_EMBQ],[NUM_UNC_TRCE_TRAN],[COD_ATTC_RET],[NUM_ECI],[NUM_XID],[NUM_CAVV],
			[COD_TIP_INTF_APCC],[NUM_VERS_TIP_INTF_APCC],TA.[COD_PLTF],[VAL_PT_ANLS_ANTFRUD],[NOM_RCMD_ANLS_ANTFRUD],[NOM_NVL_RISC_ANLS_ANTFRUD],[NOM_GTWY],TA.[NOM_MODULE]
			FROM [TRAN_ADQR] TA	
			INNER JOIN TBEC_TRCE_TRAN_MPI TM ON TM.NUM_REF_TRAN=TA.NUM_REF_TRAN
			WHERE [DAT_TRAN] >= @dateSearch
			ORDER BY [DAT_TRAN] DESC
	

	IF (@@error <> 0)
		SELECT cod_erro = @@ERROR, mensagem = 'Erro Inesperado.'

END


